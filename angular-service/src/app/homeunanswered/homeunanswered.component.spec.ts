import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeunansweredComponent } from './homeunanswered.component';

describe('HomeunansweredComponent', () => {
  let component: HomeunansweredComponent;
  let fixture: ComponentFixture<HomeunansweredComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomeunansweredComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeunansweredComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
