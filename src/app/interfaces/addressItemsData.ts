import {Time} from "@angular/common";
import {Address} from "./address";
import {Move} from "./move";
export class AddressItemsData{
  customerId: number;
  move_date: Date;
  move_time: Time;
  personal: boolean;
  place_type: string;
  cost: number;
  addresses  : Address[];
  moves:  Move[];

}
