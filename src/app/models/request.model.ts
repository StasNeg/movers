export class Request {
  id?: number;
  movedatetime: string;
  move_time: string;
  ispersonal?: boolean;
  cost?: number;
  from?: {
    seqnumber: number;
    city: string,
    street: string,
    building: string,
    apartment: string,
    floor: string
  };

  to?: {
    seqnumber: number;
    city: string,
    street: string,
    building: string,
    apartment: string,
    floor: string
  };
  addresses?: [{
    seqnumber: number;
    city: string,
    street: string,
    building: string,
    apartment: string,
    floor: string
  }];
  area?: string;
}
