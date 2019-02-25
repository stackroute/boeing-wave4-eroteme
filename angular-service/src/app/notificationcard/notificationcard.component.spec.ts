import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NotificationcardComponent } from './notificationcard.component';

describe('NotificationcardComponent', () => {
  let component: NotificationcardComponent;
  let fixture: ComponentFixture<NotificationcardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NotificationcardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NotificationcardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
