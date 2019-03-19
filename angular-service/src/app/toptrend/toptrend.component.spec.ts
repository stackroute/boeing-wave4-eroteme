import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ToptrendComponent } from './toptrend.component';

describe('ToptrendComponent', () => {
  let component: ToptrendComponent;
  let fixture: ComponentFixture<ToptrendComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ToptrendComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ToptrendComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
