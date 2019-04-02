import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchQuestionAnswerComponentComponent } from './search-question-answer-component.component';

describe('SearchQuestionAnswerComponentComponent', () => {
  let component: SearchQuestionAnswerComponentComponent;
  let fixture: ComponentFixture<SearchQuestionAnswerComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchQuestionAnswerComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchQuestionAnswerComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
