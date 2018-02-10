import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {AppComponent} from "./app.component";
import {MoveMainAddressComponent} from "./move-main-adress/move-main-address.component";
import {CommonModule} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AgmCoreModule} from "@agm/core";
import {FromToComponent} from "./move-main-adress/from-to/from-to.component";
import {AddressService} from "./services/address.service";
import {AppRoutingModule} from "./app-routing.module";
import {RoomsItemComponent} from "./room-items-main-menu/rooms-item/rooms-item.component";
import {ItemService} from "./services/item.service";
import {HttpClientModule} from "@angular/common/http";
import {MatDialogModule} from "@angular/material/dialog";
import {DialogComponent} from "./room-items-main-menu/dialog/dialogItem/dialog.component";
import {BrowserAnimationsModule, NoopAnimationsModule} from "@angular/platform-browser/animations";
import {MatButtonModule, MatFormFieldModule, MatInputModule, MatMenuModule, MatSelectModule} from "@angular/material";
import {DialogRoomComponent} from "./room-items-main-menu/dialog/dialogRoom/dialogRoom.component";
import {RoomItemsMainMenuComponent} from "./room-items-main-menu/room-items-main-menu.component";
import {LoginComponent} from "./auth/login/login.component";
import {UsersService} from "./services/users.service";
import {AuthService} from "./services/auth.service";
import {AuthGuard} from "./services/auth-guard.service";
import {RequestService} from "./services/request.service";
import {LoginService} from "./login.service";
import {FooterComponent} from "./footer/footer.component";
import {OrdersComponent} from "./orders/orders.component";
import {RequestsComponent} from "./requests/requests.component";
import {HeadersComponent} from "./header/header.component";
import {CalendarComponent} from "./calendar/calendar.component";
import {MoverComponent} from "./panel-settings/account/mover/mover.component";
import {PanelSettingsComponent} from "./panel-settings/panel-settings.component";
import {CustomerComponent} from "./panel-settings/account/customer/customer.component";
import {SettingsComponent} from "./panel-settings/settings/settings.component";
import {LurriesComponent} from "./panel-settings/account/mover/lurries/lurries.component";
import {ChoiceComponent} from "./panel-settings/account/mover/choice/choice.component";
import {AuthComponent} from "./auth/auth.component";
import {ResetPasswordComponent} from "./auth/reset-password/reset-password.component";
import {ForgotPasswordComponent} from "./auth/forgot-password/forgot-password.component";
import {ConnectionsService} from "./services/connections.service";
import {RequestComponent} from "./request/request.component";
import {RequestFinishComponent} from "./request-finish/request-finish.component";
import {RegistrationComponent} from "./auth/registration/registration.component";
import {AccountsService} from "./services/accounts.service";


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
    CalendarComponent,
    PanelSettingsComponent,
    MoverComponent,
    CustomerComponent,
    SettingsComponent,
    LurriesComponent,
    ChoiceComponent,
    AuthComponent,
    ForgotPasswordComponent,
    ResetPasswordComponent,
    RequestComponent,
    RequestFinishComponent,
    RegistrationComponent
  ],
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
    MatMenuModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule
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
    AccountsService
  ],
  bootstrap: [AppComponent],
  entryComponents: [DialogComponent, DialogRoomComponent]
})
export class AppModule {
}