import {Component, Input, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {first, map, tap} from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [HttpClient]
})
export class AppComponent implements OnInit {
  title = 'frontend';
  operations = [];

  constructor(private http: HttpClient) {
  }

  // tslint:disable-next-line:typedef
 ngOnInit() {
    this.http.get<any>('http://localhost:8080/calculator').subscribe({
      next: data => {
        this.operations = data;
        console.log(JSON.stringify(data));
      },
      error: error => {
        console.error('There was an error!', error);
      }
    });
  }

  // tslint:disable-next-line:typedef
  calculate(inputFromUser: string) {
    console.log('Input from user: ' + inputFromUser);
    const headers = {'Content-Type': 'application/json' };
    const body = {value: inputFromUser};
    this.http.post<any>('http://localhost:8080/calculator', body, {headers}).subscribe({
      next: data => {
        console.log('Result = ' + JSON.stringify(data));
        window.location.reload();
      },
      error: error => {
        console.error('There was an error!', error);
      }
    });
  }
}
