import {Address} from './address';
import {Room} from './room';

export class Move {
  addressIn: Address;
  addressOut: Address;
  rooms: Room[];
}
