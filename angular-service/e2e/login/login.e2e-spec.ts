import { browser, logging } from 'protractor';
import { LoginPage } from './login.po';

describe('workspace-project App', () => {
  let page: LoginPage;

  beforeEach(() => {
    page = new LoginPage();
  });

  it('should display first main heading', () => {
    page.navigateToLogin();
    expect(page.getLoginText().getText()).toEqual('Login');
  });

  it('should display Login Text', () => {
    page.navigateToLogin();
    expect(page.getLoginButtonText().getText()).toEqual('Login');
  });

  it('should display Signup text',()=>{
    page.navigateToLogin();
    expect(page.getSignUpText().getText()).toEqual('Signup');
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    }));
  });
});