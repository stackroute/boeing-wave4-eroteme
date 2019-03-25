import { browser, by, element } from 'protractor';

export class SignupPage {
  navigateToSignup() {
    return browser.get('/register') as Promise<any>;
  }

  getSignupText(){
      return element(by.css('app-register p'));
  }

}
