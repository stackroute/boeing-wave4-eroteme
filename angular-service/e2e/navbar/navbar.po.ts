import { browser, by, element } from 'protractor';

export class NavPage {
  navigateToNavbar() {
    return browser.get('/navbar') as Promise<any>;
  }

  getLoginText() {
    return element(by.css('[routerLink="/login"]'));
  }

  getSignupText() {
    return element(by.css('[routerLink="/register"]'));
  }
}
