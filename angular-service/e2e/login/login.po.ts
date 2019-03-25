import { browser, by, element } from 'protractor';

export class LoginPage {
  navigateToLogin() {
    return browser.get('/login') as Promise<any>;
  }

  getLoginText(){
      return element(by.css('app-login h3'));
  }

  getLoginButtonText(){
      return element(by.buttonText('Login'));
  }

  getSignUpText(){
      return element(by.buttonText('Signup'));
  }
}
