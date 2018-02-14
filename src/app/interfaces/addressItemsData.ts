import {Address} from "./address";
import {Move} from "./move";
export class AddressItemsData{
  customerId: number;
  move_date: string;
  move_time: string;
  personal: boolean;
  place_type: string;
  cost: number;
  addresses  : Address[];
  moves:  Move[];

}
