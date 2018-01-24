import {ItemAddressData} from './itemAddress-data';
import {Room} from './room';

export interface Mover {
  addressFrom: ItemAddressData;
  addressTo: ItemAddressData;
  rooms: Room[];
}
