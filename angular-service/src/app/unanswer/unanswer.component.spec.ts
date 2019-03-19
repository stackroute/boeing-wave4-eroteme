import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UnanswerComponent } from './unanswer.component';

describe('UnanswerComponent', () => {
  let component: UnanswerComponent;
  let fixture: ComponentFixture<UnanswerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnanswerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnanswerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
