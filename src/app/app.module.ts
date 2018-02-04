import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {MoveMainAddressComponent} from './move-main-adress/move-main-address.component';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AgmCoreModule} from '@agm/core';
import {FromToComponent} from './move-main-adress/from-to/from-to.component';
import {AddressService} from './services/address.service';
import {AppRoutingModule} from './app-routing.module';
import {RoomsItemComponent} from './room-items-main-menu/rooms-item/rooms-item.component';
import {ItemService} from './services/item.service';
import {HttpClientModule} from '@angular/common/http';
import {MatDialogModule} from '@angular/material/dialog';
import {DialogComponent} from './room-items-main-menu/dialog/dialogItem/dialog.component';
import {BrowserAnimationsModule, NoopAnimationsModule} from '@angular/platform-browser/animations';
import {MatMenuModule, MatSelectModule} from '@angular/material';
import {DialogRoomComponent} from './room-items-main-menu/dialog/dialogRoom/dialogRoom.component';
import {RoomItemsMainMenuComponent} from './room-items-main-menu/room-items-main-menu.component';
import {LoginComponent} from './auth/login/login.component';
import {UsersService} from './services/users.service';
import {AuthService} from './services/auth.service';
import {AuthGuard} from './services/auth-guard.service';
import {RequestService} from './request.service';
import {LoginService} from './login.service';
import {FooterComponent} from './footer/footer.component';
import {OrdersComponent} from './orders/orders.component';
import {RequestsComponent} from './requests/requests.component';
import {HeadersComponent} from './header/header.component';
import {CalendarComponent} from './calendar/calendar.component';


@NgModule({
  declarations: [
    AppComponent,
    MoveMainAddressComponent,
    FromToComponent,
    RoomItemsMainMenuComponent,
    RoomsItemComponent,
    DialogComponent,
    DialogRoomComponent,
    LoginComponent,
    FooterComponent,
    OrdersComponent,
    RequestsComponent,
    HeadersComponent,
    CalendarComponent],
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyC4NCBOr2KvWwLGTa1ZwU8V7ZtIuOrInPY',
      libraries: ['places']
    }),
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule,
    NoopAnimationsModule,
    MatSelectModule,
    MatMenuModule
  ],
  providers: [
    AddressService,
    ItemService,
    UsersService,
    AuthService,
    AuthGuard,
    RequestService,
    LoginService],
  bootstrap: [AppComponent],
  entryComponents: [DialogComponent, DialogRoomComponent]
})
export class AppModule {
}
