import { browser, logging } from 'protractor';
import { SignupPage } from './signup.po';

describe('workspace-project App', () => {
  let page: SignupPage;

  beforeEach(() => {
    page = new SignupPage();
  });

  it('should display first main heading', () => {
    page.navigateToSignup();
    expect(page.getSignupText().getText()).toEqual('Registration');
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    }));
  });
});