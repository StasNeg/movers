export class DataModel {
  customerId: number;
  move_date: string;
  personal: boolean;
  place_type: string;
  cost?: number;
  addresses: {
    seqnumber: number;
    latitude: number;
    longitude: number;
    city: string;
    street: string;
    building: string;
    apartment?: string;
    floor: string;
    lift: string;
  };
  moves: {

    addressIn: {
      seqnumber: number;
      latitude: number;
      longitude: number;
      city: string;
      street: string;
      building: string;
      apartment?: string;
      floor: string;
      lift: string;
    },
    addressOut: {
      seqnumber: number;
      latitude: number;
      longitude: number;
      city: string;
      street: string;
      building: string;
      apartment?: string;
      floor: string;
      lift: string;
    };
    rooms: {
      room: string;
      items: {
        name: string;
        properties: {
          size: string
        }
      }
    }
  };
}
