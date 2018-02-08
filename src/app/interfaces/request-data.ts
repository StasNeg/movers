import {Move} from './move';
import {Address} from './address';

export class RequestData {
  customerId: number;
  move_date: string;
  move_time: string;
  personal: boolean;
  place_type: string;
  cost: number;
  addresses: Address[];
  moves: Move[];
}
