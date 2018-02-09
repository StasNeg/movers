export class Account {
  constructor(
    public email: string,
    public password?: string,
    public name?: string,
    public type?: string,
    public phone?: number,
    public enabled?: boolean,
    public id?: number
  ) {}
}
