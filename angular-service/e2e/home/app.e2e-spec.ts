import { AppPage } from './app.po';
import { browser, logging } from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display first main heading', () => {
    page.navigateTo();
    expect(page.getTitleText()).toEqual('Top Trending Questions');
  });

  it('should display the product name', () => {
    page.navigateTo();
    expect(page.getProductText()).toEqual('Eroteme:');
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    }));
  });
});
