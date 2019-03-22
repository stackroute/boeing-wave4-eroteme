import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomejumbotronComponent } from './homejumbotron.component';

describe('HomejumbotronComponent', () => {
  let component: HomejumbotronComponent;
  let fixture: ComponentFixture<HomejumbotronComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomejumbotronComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomejumbotronComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
