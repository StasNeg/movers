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
import {ItemService} from './services/item.service';
import {HttpClientModule} from '@angular/common/http';
import {MatDialogModule} from '@angular/material/dialog';
import {DialogComponent} from './room-items-main-menu/dialog/dialogItem/dialog.component';
import {BrowserAnimationsModule, NoopAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule, MatFormFieldModule, MatInputModule, MatMenuModule, MatSelectModule} from '@angular/material';
import {DialogRoomComponent} from './room-items-main-menu/dialog/dialogRoom/dialogRoom.component';
import {RoomItemsMainMenuComponent} from './room-items-main-menu/room-items-main-menu.component';
import {LoginComponent} from './auth/login/login.component';
import {UsersService} from './services/users.service';
import {AuthService} from './services/auth.service';
import {AuthGuard} from './services/auth-guard.service';
import {RequestService} from './services/request.service';
import {LoginService} from './login.service';
import {FooterComponent} from './footer/footer.component';
import {OrdersComponent} from './orders/orders.component';
import {RequestsComponent} from './requests/requests.component';
import {HeadersComponent} from './header/header.component';
import {CalendarComponent} from './calendar/calendar.component';
import {PanelSettingsComponent} from './panel-settings/panel-settings.component';
import {CustomerComponent} from './panel-settings/account/customer/customer.component';
import {SettingsComponent} from './panel-settings/settings/settings.component';
import {ChoiceComponent} from './panel-settings/account/mover/choice/choice.component';
import {AuthComponent} from './auth/auth.component';
import {ResetPasswordComponent} from './auth/reset-password/reset-password.component';
import {ForgotPasswordComponent} from './auth/forgot-password/forgot-password.component';
import {ConnectionsService} from './services/connections.service';
import {RequestComponent} from './request/request.component';
import {RequestFinishComponent} from './request-finish/request-finish.component';
import {RegistrationComponent} from './auth/registration/registration.component';
import {AccountsService} from './services/accounts.service';
import {TrucksComponent} from './trucks/trucks.component';
import {TrucksService} from './services/trucks.service';
import {TruckEditmenuComponent} from './truckeditmenu/truckeditmenu.component';
import {BootstrapModalModule} from 'ng2-bootstrap-modal';
import { NKDatetimeModule } from 'ng2-datetime/ng2-datetime';
import {AddAdditionalAddressComponent} from "./move-main-adress/additional-address-modal-form/dialogAddAdditionalAddress.component";
import {RequestsMoverComponent} from "./requests-mover/requests-mover.component";
import {RequestDetailDialog} from "./requests-mover/requests-detail-modal/requests-detail-modal.component";

@NgModule({
  declarations: [
    AppComponent,
    MoveMainAddressComponent,
    FromToComponent,
    RoomItemsMainMenuComponent,
    DialogComponent,
    DialogRoomComponent,
    LoginComponent,
    FooterComponent,
    OrdersComponent,
    RequestsComponent,
    HeadersComponent,
    CalendarComponent,
    PanelSettingsComponent,
    CustomerComponent,
    SettingsComponent,
    ChoiceComponent,
    AuthComponent,
    ForgotPasswordComponent,
    ResetPasswordComponent,
    RequestComponent,
    RequestFinishComponent,
    RegistrationComponent,
    TrucksComponent,
    TruckEditmenuComponent,
    AddAdditionalAddressComponent,
    RequestsMoverComponent,
    RequestDetailDialog
  ],
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    BootstrapModalModule,
    BootstrapModalModule.forRoot({container: document.body}),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyC4NCBOr2KvWwLGTa1ZwU8V7ZtIuOrInPY',
      libraries: ['places']
    }),
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule,
    NoopAnimationsModule,
    MatSelectModule,
    MatMenuModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    NKDatetimeModule
  ],
  providers: [
    AddressService,
    ItemService,
    UsersService,
    AuthService,
    AuthGuard,
    RequestService,
    LoginService,
    AuthService,
    ConnectionsService,
    AccountsService,
    TrucksService

  ],
  bootstrap: [AppComponent],
  entryComponents: [DialogComponent, DialogRoomComponent, TruckEditmenuComponent, AddAdditionalAddressComponent, RequestDetailDialog]
})
export class AppModule {
}
