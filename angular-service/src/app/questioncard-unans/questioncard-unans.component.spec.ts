import{async, ComponentFixture, TestBed}from '@angular/core/testing';

import {QuestioncardUnansComponent} from './questioncard-unans.component';

describe('QuestioncardUnansComponent', () => {
  let component: QuestioncardUnansComponent;
  let fixture: ComponentFixture<QuestioncardUnansComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuestioncardUnansComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuestioncardUnansComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
