import {Room} from './room';

export interface ItemAddressData {
  id?: number;
  lat?: number;
  lng?: number;
  city: any;
  street: any;
  rooms: Room[];
}
