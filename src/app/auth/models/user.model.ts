export class User {
  constructor(public email: string,
              public phone: string,
              public firstName: string,
              public password: string,
              public enabled: boolean,
              public name: string,
              public lang: string,
              public type: string,
              public id: number) {
  }
}
