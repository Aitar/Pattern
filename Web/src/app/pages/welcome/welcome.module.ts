import { NgModule } from '@angular/core';

import { WelcomeRoutingModule } from './welcome-routing.module';

import { WelcomeComponent } from './welcome.component';
import {NzInputModule} from "_ng-zorro-antd@11.4.2@ng-zorro-antd/input";
import {FormsModule} from "_@angular_forms@11.2.14@@angular/forms";
import {NzButtonModule} from "_ng-zorro-antd@11.4.2@ng-zorro-antd/button";


@NgModule({
  imports: [WelcomeRoutingModule, NzInputModule, FormsModule, NzButtonModule],
  declarations: [WelcomeComponent],
  exports: [WelcomeComponent]
})
export class WelcomeModule { }
