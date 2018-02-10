import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {ItemService} from '../../../services/item.service';


@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {

  private itemTypes;
  private itemTypeSelect = '';
  private itemProperties = [];
  private property = [];
  private resultItemType: { item: any, property?: any };
  private isItemProperties = false;

  constructor(public dialogRef: MatDialogRef<DialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any, private itemService: ItemService) {
  }

  ngOnInit() {
    this.itemService.getItemsType(this.data.roomType).subscribe(res => {
      this.itemTypes = res.data;
    });

  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  itemTypeOnChange() {
    if (this.itemTypeSelect !== '') {
      this.itemService.getItemsProperties(this.getId())
        .subscribe((res) => {
          this.itemProperties = [];
          this.isItemProperties = true;
          for (let data in <Array<object>>res.data) {
            this.itemProperties.push({name: res.data[data].name, properties: res.data[data].value.split('|')});
          }
        });
    } else {
      this.isItemProperties = false;
    }
  }


  private getId() {
    for (let i = 0; i < this.itemTypes.length; i++) {
      if (this.itemTypes[i].name === this.itemTypeSelect) {
        this.resultItemType = {item: this.itemTypes[i]};
        return this.itemTypes[i].id;
      }
    }
  }

  itemPropertiesOnChange(type, event) {
    this.property[type] = event;
  }

  closeForm(num) {
    if (num === 1) {
      this.resultItemType.property = this.property;
      this.dialogRef.close(this.resultItemType);
    } else {
      this.dialogRef.close(null);
    }
   }
}
