import {Component, OnInit} from '@angular/core';
import {TrucksService} from '../services/trucks.service';
import {TruckModel} from '../models/truck.model';
import {DialogService} from 'ng2-bootstrap-modal';
import {DatatrucksModel} from '../models/datatrucks.model';
import {TruckEditmenuComponent} from '../truckeditmenu/truckeditmenu.component';


@Component({
  selector: 'app-trucks',
  templateUrl: './trucks.component.html',
  styleUrls: ['./trucks.component.css']
})


export class TrucksComponent implements OnInit {
  constructor(private truckService: TrucksService, private dialogService: DialogService) {
  }

  truckObject = new TruckModel();
  trucks = this.truckObject.data;
  trucksMenu = ['#', 'Area', 'Driver Id', 'Moves By Day', 'Count of Rooms', 'Type of Trucks'];

  ngOnInit() {
    this.truckService.getTrucks().subscribe((res: TruckModel) => {
      this.trucks = res.data;
      console.log(this.trucks);
    });
  }

  showConfirm(truck: DatatrucksModel) {
    let disposable = this.dialogService.addDialog(TruckEditmenuComponent, {
      title: 'Confirm title',
      message: 'Confirm message',
      trucks: Object.assign({}, truck)
    })
      .subscribe((trucks) => {
        if (trucks) {
          this.truckService.changeTruck(trucks).subscribe((data: DatatrucksModel) => {
            console.log(data);
            this.truckService.getTrucks().subscribe((resp: TruckModel) => {
              this.trucks = resp.data;
            });
          });
        } else {
          alert('You didnt save');
        }

      });
  }

  deleteTruck(truck) {
    console.log(truck.id);
    this.truckService.deleteTruck(truck).subscribe(data => {

      this.trucks = this.trucks.filter(c => c.id !== truck.id);
    });
  }


}
