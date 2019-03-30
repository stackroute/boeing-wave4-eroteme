import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PostalertComponent } from './postalert.component';

describe('PostalertComponent', () => {
  let component: PostalertComponent;
  let fixture: ComponentFixture<PostalertComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PostalertComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PostalertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
