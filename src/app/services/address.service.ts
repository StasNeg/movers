export class AddressService {
  public addressesTo = [];
  public addressFrom = null;
  public addressTo = null;
  public addressAdd = null;
  public typeAppartment = null;

  public isCorrectForm() {
    if (this.addressFrom === null)
      return false;
    if (this.addressTo === null)
      return false;
    return true;
  }

  public isCorrectAdd() {
    if (this.addressTo === null || this.addressFrom === null)
      return false;
    return true;
  }


  swithCorrect(isFromTo) {
    if (isFromTo===1) {
      if (this.addressFrom === null)
        return false;
    } else if (isFromTo===2){
      if (this.addressTo === null)
        return false;
    } else {
      if (this.addressAdd === null)
        return false;
    }
    return true;
  }

  save(isFromTo, info, floor, haveLift, makePacking) {
    if (isFromTo === 1){
      this.saveFrom(info, floor, haveLift, makePacking);
      return;
    }
    if(isFromTo === 2){
      this.saveTo(info, floor, haveLift);
      return;
    }
    if(isFromTo === 3){
      this.saveAdd(info, floor, haveLift);
      return;
    }
    // isFromTo ? this.saveFrom(info, floor, haveLift, makePacking) : this.saveTo(info, floor, haveLift);
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
      packaging: makePacking
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
  saveAdd(info, floor, haveLift) {
    let langlong = info.geometry.location;
    this.addressAdd = {
      street: info.address_components[1].long_name,
      street_number: info.address_components[0].long_name,
      city: info.address_components[2].long_name,
      lat: langlong.toJSON().lat,
      lng: langlong.toJSON().lng,
      floor: floor,
      lift: haveLift
    };
  }

  addAddressAdd() {
    this.addressesTo.push(this.addressAdd);
    this.addressAdd = null;
  }

  clear(isFromTo) {
    switch (isFromTo){
      case 1:
        this.addressFrom = null;
        break;
      case 2:
        this.addressTo = null;
        break;
      case 3:
        this.addressAdd = null;
        break;
    }
  }

  changeLift(isFromTo, lift) {
    switch (isFromTo){
      case 1:
        this.addressFrom.lift = lift
        break;
      case 2:
        this.addressTo.lift = lift
        break;
      case 3:
        this.addressAdd.lift = lift
        break;
    }

  }

  changeFloor(isFromTo, floor) {
    switch (isFromTo){
      case 1:
        this.addressFrom.floor = floor;
        break;
      case 2:
        this.addressTo.floor = floor;
        break;
      case 3:
        this.addressAdd.floor = floor;
        break;
    }
  }

  changePacking(packing) {
    this.addressFrom.packaging = packing;
  }

  clearAddress(){
    this.addressFrom = null;
    this.addressTo = null;
    this.addressesTo = [];
  }
}
