import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeTaskMangerComponent } from './employee-task-manger.component';

describe('EmployeeTaskMangerComponent', () => {
  let component: EmployeeTaskMangerComponent;
  let fixture: ComponentFixture<EmployeeTaskMangerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeeTaskMangerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployeeTaskMangerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
