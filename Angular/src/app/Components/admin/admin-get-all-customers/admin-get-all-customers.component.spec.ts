import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminGetAllCustomersComponent } from './admin-get-all-customers.component';

describe('AdminGetAllCustomersComponent', () => {
  let component: AdminGetAllCustomersComponent;
  let fixture: ComponentFixture<AdminGetAllCustomersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminGetAllCustomersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminGetAllCustomersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
