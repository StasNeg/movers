export class AddressService {
  public addressesTo = [];
  public addressFrom = null;
  public addressTo = null;
  public typeAppartment = null;

  public isCorrectForm() {
    if (this.addressFrom === null)
      return false;
    if (this.addressesTo.length === 0)
      return false;
    return true;
  }

  public isCorrectAdd() {
    if (this.addressTo === null)
      return false;
    return true;
  }


  swithCorrect(isFromTo) {
    if (isFromTo) {
      if (this.addressFrom === null)
        return false;
    } else {
      if (this.addressTo === null)
        return false;
    }
    return true;
  }

  save(isFromTo, info, floor, haveLift, makePacking) {
    isFromTo ? this.saveFrom(info, floor, haveLift, makePacking) : this.saveTo(info, floor, haveLift);
  }

  saveFrom(info, floor, haveLift, makePacking) {
    let langlong = info.geometry.location;
    this.addressFrom = {
      street: info.address_components[1].long_name,
      street_number: info.address_components[0].long_name,
      city: info.address_components[2].long_name,
      lat: langlong.toJSON().lat,
      lng: langlong.toJSON().lng,
      floor: floor,
      lift: haveLift,
      packaging: makePacking,

    };
  }

  saveTo(info, floor, haveLift) {
    let langlong = info.geometry.location;
    this.addressTo = {
      street: info.address_components[1].long_name,
      street_number: info.address_components[0].long_name,
      city: info.address_components[2].long_name,
      lat: langlong.toJSON().lat,
      lng: langlong.toJSON().lng,
      floor: floor,
      lift: haveLift
    };
  }

  addTo() {
    this.addressesTo.push(this.addressTo);
    this.addressTo = null;
  }

  clear(isFromTo) {
    isFromTo ? this.addressFrom = null : this.addressTo = null;
  }

  changeLift(isFromTo, lift) {
    isFromTo ? this.addressFrom.lift = lift : this.addressTo.lift = lift;
  }

  changeFloor(isFromTo, floor) {
    isFromTo ? this.addressFrom.floor = floor : this.addressTo.floor = floor;
  }

  changePacking(packing) {
    this.addressFrom.packaging = packing;
  }

  createAddressesArray() {
    let ad = [];
    if (this.addressFrom == null && this.addressesTo.length == 0)
      return this.defaultMenu();
    ad.push({
      street: this.addressFrom.street,
      city: this.addressFrom.city
    });
    for (let i = 0; i < this.addressesTo.length - 1; i++) {
      ad.push({
        street: this.addressesTo[i].street,
        city: this.addressesTo[i].city
      });
    }
    return ad;
  }

  defaultMenu() {
    let ad = [];
    for (let i = 0; i < 8; i++) {
      ad.push({
        street: 'Street: ' + i,
        city: 'City: ' + i
      });
    }
    return ad;
  }

}
