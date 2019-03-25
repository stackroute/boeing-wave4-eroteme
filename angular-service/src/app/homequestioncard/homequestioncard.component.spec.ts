import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomequestioncardComponent } from './homequestioncard.component';

describe('HomequestioncardComponent', () => {
  let component: HomequestioncardComponent;
  let fixture: ComponentFixture<HomequestioncardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomequestioncardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomequestioncardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
