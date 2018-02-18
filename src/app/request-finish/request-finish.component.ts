import {Component, OnInit} from '@angular/core';
import {ConnectionsService} from '../services/connections.service';
import {ItemService} from "../services/item.service";

@Component({
  selector: 'app-request-finish',
  templateUrl: './request-finish.component.html',
  styleUrls: ['./request-finish.component.css']
})
export class RequestFinishComponent implements OnInit {

  requestData: any =  this.itemService.getItemsOrder();
    // require('../../assets/data.json');
  addressesToAndItems;

  cartons: number;
  packets: number;

  cartons_checked = true;
  packets_checked = true;
  terms_checked = false;
  pageNum = 'page1';

  cartonPrice: number;
  packetPrice: number;
  initialCost: number;
  finalCost: number;

  constructor(private connectionsService: ConnectionsService, private itemService:ItemService) {
  }

  ngOnInit() {
    this.cartons = 40;
    this.packets = 10;
    this.addressesToAndItems = this.itemService.getShowingData(this.requestData);
    this.connectionsService.getTotalCostEstimate(this.requestData).subscribe((res) => {
      if (res.success === true) {
        this.cartonPrice = res.data['cartonPrice'];
        this.packetPrice = res.data['packetPrice'];
        this.initialCost = res.data['totalCost'];
        this.finalCost = this.initialCost + (this.cartons * this.cartonPrice) + (this.packets * this.packetPrice);
      }
    });
  }

  onChange() {
    if (this.packets_checked === true && this.cartons_checked === true) {
      this.finalCost = this.initialCost + (this.cartons * this.cartonPrice) + (this.packets * this.packetPrice);
    }
    if (this.packets_checked === true && this.cartons_checked === false) {
      this.finalCost = this.initialCost + (this.packets * this.packetPrice);
    }
    if (this.packets_checked === false && this.cartons_checked === true) {
      this.finalCost = this.initialCost + (this.cartons * this.cartonPrice);
    }
    if (this.packets_checked === false && this.cartons_checked === false) {
      this.finalCost = this.initialCost;
    }
  }

  saveRequest() {
    this.requestData.cost = this.finalCost;
    this.connectionsService.saveRequest(this.requestData)
      .subscribe((response) => {
        alert(response.data);
      });
  }

}
