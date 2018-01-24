import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {ItemService} from "../../../services/item.service";


@Component({
  selector: 'app-dialog',
  templateUrl: './dialogRoom.component.html',
  styleUrls: ['./dialogRoom.component.css']
})
export class DialogRoomComponent implements OnInit {

  private room;
  private roomTypes;

  constructor(public dialogRef: MatDialogRef<DialogRoomComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any, private itemService: ItemService) {
  }

  ngOnInit() {
    this.roomTypes = this.data;
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  closeForm(num) {
    if (num === 1 && this.room != '') {
      this.dialogRef.close(this.room);
    } else
      this.dialogRef.close(null);
  }
}
