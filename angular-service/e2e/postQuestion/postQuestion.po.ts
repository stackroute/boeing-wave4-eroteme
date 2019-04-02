import { browser, by, element } from 'protractor';

export class POstQuestionPage {
  navigateTopostQuestion() {
    return browser.get('/postQuestion') as Promise<any>;
  }

  getLabelText() {
    return element(by.css('app-post-question label'));
  }

  getpostButton(){
    return element(by.buttonText('Post your Question!'));
  }
}
