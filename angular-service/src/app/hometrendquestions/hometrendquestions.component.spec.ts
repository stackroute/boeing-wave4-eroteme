import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HometrendquestionsComponent } from './hometrendquestions.component';

describe('HometrendquestionsComponent', () => {
  let component: HometrendquestionsComponent;
  let fixture: ComponentFixture<HometrendquestionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HometrendquestionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HometrendquestionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
