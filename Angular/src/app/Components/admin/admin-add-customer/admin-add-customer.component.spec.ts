import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAddCustomerComponent } from './admin-add-customer.component';

describe('AdminAddCustomerComponent', () => {
  let component: AdminAddCustomerComponent;
  let fixture: ComponentFixture<AdminAddCustomerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminAddCustomerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminAddCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
