import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import * as moment from 'moment';
import * as _ from 'lodash';
import {RequestService} from '../request.service';
import {Request} from '../models/request.model';
import {Router} from '@angular/router';

export interface CalendarDate {
  mDate: moment.Moment;
  selected?: boolean;
  today?: boolean;
  request?: Request[];
}

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.scss']

})

export class CalendarComponent implements OnInit, OnChanges {
  currentDate = moment();
  dayNames = ['Sn', 'Mn', 'Tu', 'Wd', 'Th', 'Fr', 'St'];
  weeks: CalendarDate[][] = [];
  sortedDates: CalendarDate[] = [];
  requests: Request[];

  @Input() selectedDates: CalendarDate[] = [];
  @Output() onSelectDate = new EventEmitter<CalendarDate>();
  maximumOfMarks: number = 4;

  constructor(private requestService: RequestService, private router: Router) {
  }

  ngOnInit(): void {

    this.requestService.getRequests().subscribe(
      (requests: Request []) => {
        this.requests = requests;
        this.requests.forEach(request => {
          this.selectedDates.push({
            mDate: moment(request.movedatetime),
            selected: true,
          });
        });
        this.generateCalendar();
      });

  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.selectedDates &&
      changes.selectedDates.currentValue &&
      changes.selectedDates.currentValue.length > 1) {
      // sort on date changes for better performance when range checking
      this.sortedDates = _.sortBy(changes.selectedDates.currentValue, (m: CalendarDate) => m.mDate.valueOf());
      this.generateCalendar();
    }
  }

  // date checkers
  isToday(date: moment.Moment): boolean {
    return moment().isSame(moment(date), 'day');
  }

  isSelected(date: moment.Moment): boolean {
    return _.findIndex(this.selectedDates, (selectedDate) => {
      return moment(date).isSame(selectedDate.mDate, 'day');
    }) > -1;
  }

  isSelectedMonth(date: moment.Moment): boolean {
    return moment(date).isSame(this.currentDate, 'month');
  }

  selectDate(date: CalendarDate): void {
    event.preventDefault();
    if (date.selected) {
      this.router.navigate(['/orders', moment(date.mDate).format('LL')]);
    }
    this.onSelectDate.emit(date);
  }

  // actions from calendar
  prevMonth(): void {
    this.currentDate = moment(this.currentDate).subtract(1, 'months');
    this.generateCalendar();
  }

  nextMonth(): void {
    this.currentDate = moment(this.currentDate).add(1, 'months');
    this.generateCalendar();
  }

  firstMonth(): void {
    this.currentDate = moment(this.currentDate).startOf('year');
    this.generateCalendar();
  }

  lastMonth(): void {
    this.currentDate = moment(this.currentDate).endOf('year');
    this.generateCalendar();
  }

  prevYear(): void {
    this.currentDate = moment(this.currentDate).subtract(1, 'year');
    this.generateCalendar();
  }

  nextYear(): void {
    this.currentDate = moment(this.currentDate).add(1, 'year');
    this.generateCalendar();
  }

  // generate the calendar grid
  generateCalendar(): void {
    const dates = this.fillDates(this.currentDate);
    const weeks: CalendarDate[][] = [];
    while (dates.length > 0) {
      weeks.push(dates.splice(0, 7));
    }
    this.weeks = weeks;
  }

  isRequest(day) {
    let curRequest = [new Request()];
    this.requests.forEach(request => {
      let d = day.format('LL');
      let nd = moment(request.movedatetime).format('LL');
      if (d === nd) {
        curRequest.push(request);
      }
    });
    return curRequest.length ? curRequest : [new Request()];
  }

  fillDates(currentMoment: moment.Moment): CalendarDate[] {
    const firstOfMonth = moment(currentMoment).startOf('month').day();
    const firstDayOfGrid = moment(currentMoment).startOf('month').subtract(firstOfMonth, 'days');
    const start = firstDayOfGrid.date();
    return _.range(start, start + 42)
      .map((date: number): CalendarDate => {
        const d = moment(firstDayOfGrid).date(date);
        const request = this.isRequest(d);
        return {
          today: this.isToday(d),
          selected: this.isSelected(d),
          mDate: d,
          request: request
        };
      });
  }
}
