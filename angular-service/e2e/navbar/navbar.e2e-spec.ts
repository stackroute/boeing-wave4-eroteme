import { browser, logging } from 'protractor';
import { NavPage } from './navbar.po';

describe('workspace-project App', () => {
  let page: NavPage;

  beforeEach(() => {
    page = new NavPage();
  });

  it('should display first main heading', () => {
    page.navigateToNavbar();
    browser.pause();
    expect(page.getLoginText().getText()).toEqual('Login');
  });

  it('it should match signup text',()=>{
      page.navigateToNavbar();
      expect(page.getSignupText().getText()).toEqual('Sign Up');
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    }));
  });
});