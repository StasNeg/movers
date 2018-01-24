import {Component, ElementRef, NgZone, OnInit, ViewChild, Input} from '@angular/core';
import {MapsAPILoader} from "@agm/core";
import {FormControl} from '@angular/forms';
import {AddressService} from '../../services/address.service'
import {Subject} from "rxjs/Subject";


@Component({
  selector: 'app-from-to',
  templateUrl: './from-to.component.html',
  styleUrls: ['./from-to.component.css']
})
export class FromToComponent implements OnInit {

  @Input() fromTo;
  @ViewChild("search")
  private searchElementRef: ElementRef;

  @Input()
  parentSubject:Subject<any>;
  private searchControl: FormControl;

  private lat;
  private lng;
  private zoom: number;
  private openMap = false;
  private isFromTo;
  private title;
  public haveLift = false;
  public makePacking = false;
  public floor = 1;

  constructor(private mapsAPILoader: MapsAPILoader,
              private ngZone: NgZone,
              private addressService: AddressService) {
  }

  ngOnInit() {
    this.zoom = 17;
    this.searchControl = new FormControl('', null, this.checkValidAddress.bind(this));
    this.parentSubject.subscribe(event => {
      this.clearAddress();
    });
    this.setCurrentPosition();
    this.setFromTo();
    this.mapsAPILoader.load().then(() => {
      let autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement, {
        componentRestrictions: {country: 'IL'},
        types: ["address"]
      });
      autocomplete.addListener("place_changed", () => {
        this.ngZone.run(() => {
          //get the place result
          let place: google.maps.places.PlaceResult = autocomplete.getPlace();
          //verify result
          if (place.geometry === undefined || place.geometry === null) {
            return;
          }
          //set latitude, longitude and zoom
          this.lat = place.geometry.location.lat();
          this.lng = place.geometry.location.lng();
        });
      });
    });
  }

  private setCurrentPosition() {
    if ("geolocation" in navigator) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.lat = position.coords.latitude;
        this.lng = position.coords.longitude;
      });
    }
  }

  public onMapClick(event) {
    this.lat = event.coords.lat;
    this.lng = event.coords.lng;
  }


  public onLocationClick() {
    this.openMap = true;
  }

  private codeLatLng(lat, lng, callback) {
    var latlng = new google.maps.LatLng(lat, lng);
    var geocoder = geocoder = new google.maps.Geocoder();
    geocoder.geocode({'latLng': latlng}, function (results, status) {
      if (status == google.maps.GeocoderStatus.OK) {

        if (results[0]) {
          callback(results);
        }
      }
    });
  }

  public saveMarkers() {
    this.openMap = false;
    this.codeLatLng(this.lat, this.lng, (result) => {
      this.searchElementRef.nativeElement.value = result.formatted_address;
      this.searchControl.setValue(result[0].formatted_address);
    });
  }

  private setFromTo() {
    if (this.fromTo !== 'to') {
      this.isFromTo = true;
      this.title = 'From'
    }
    else {
      this.isFromTo = false;
      this.title = 'To'
    }
  }

  public checkByAddress(control: FormControl, callback) {
    var geocoder = geocoder = new google.maps.Geocoder();
    var location = control.value;
    geocoder.geocode({'address': location, 'componentRestrictions': {country: 'IL'}}, function (results, status) {
      if (status == google.maps.GeocoderStatus.OK && results[0].types.includes('street_address')) {
        callback(results[0]);
      }
      else {
        callback('NO');
      }
    });
  }

  public checkValidAddress(control: FormControl): Promise<any> {
    return new Promise((res, rej) => {
      this.checkByAddress(control, (info) => {
        if (info === 'NO') {
          this.addressService.clear(this.isFromTo);
          res({
            'wrongAddress': true
          });
        }
        else {
          let langlong = info.geometry.location;
          this.lat = langlong.toJSON().lat;
          this.lng = langlong.toJSON().lng;
          this.addressService.save(this.isFromTo, info, this.floor, this.haveLift, this.makePacking);
          res(null);
        }
      });
    });
  }

  closeMarkers() {
    this.openMap = false;
  }

  isCorrectForSwitch() {
    return this.addressService.swithCorrect(this.isFromTo);
  }

  makePackingOnclick(){
    this.addressService.changePacking(this.makePacking);
  }

  haveLiftOnClick(){
    this.addressService.changeLift(this.isFromTo, this.haveLift);
  }

  floorOnChange(){
    this.addressService.changeFloor(this.isFromTo, this.floor);
  }

  clearAddress(){
    if(!this.isFromTo) {
      this.searchElementRef.nativeElement.value = '';
      this.floor = 1;
      this.haveLift = false;
    }
  }
}
