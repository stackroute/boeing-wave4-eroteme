import { browser, logging } from 'protractor';
import { POstQuestionPage } from './postQuestion.po';

describe('workspace-project App', () => {
  let page: POstQuestionPage;

  beforeEach(() => {
    page = new POstQuestionPage();
  });

  it('should display first main heading', () => {
    page.navigateTopostQuestion();
    expect(page.getLabelText().getText()).toEqual('Question:');
  });

  it('should match witht the button values',()=>{
    page.navigateTopostQuestion();
    expect(page.getpostButton().getText()).toEqual('Post your Question!');
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    }));
  });
});