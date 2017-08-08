export class User {
  constructor(
    public username: string,
    public firstName: string,
    public lastName: string,
    public password: string,
    public confirmPassword: string
  ){}
}