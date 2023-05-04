import {AfterViewInit, Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {BasicProjectDTO} from 'src/app/DTOs/projectDTOs';
import {ProjektService} from 'src/app/services/project-service/projekt.service';
import {ChartConfiguration} from "chart.js";
import {MatDialog} from "@angular/material/dialog";
import {ProjectSettingsComponent} from "./project-settings/project-settings.component";

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss']
})
export class ProjectComponent implements OnInit {
  @Input() basicProjectDTO!: BasicProjectDTO;

  // Doughnut
  public doughnutChartLabels: string[] = [];
  public doughnutChartDatasets: ChartConfiguration<'doughnut'>['data']['datasets'] = [];

  public doughnutChartOptions: ChartConfiguration<'doughnut'>['options'] = {
    responsive: false
  };
  progress: number = 0;

  constructor(
    private projectService: ProjektService,
    private router: Router,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    let numberOfTasks: number = 0;
    let dataset: number[] = [];
    let doneTaskCount: number = 0;
    this.basicProjectDTO.lists.forEach((list) => {
      numberOfTasks += list.taskCount;
      this.doughnutChartLabels.push(list.name)
      dataset.push(list.taskCount)
      if (list.name === "Done") {
        doneTaskCount = list.taskCount
      }
    })

    this.progress = (numberOfTasks !== 0 || doneTaskCount !== 0) ? ((doneTaskCount / numberOfTasks) * 100) : 0;
    this.doughnutChartDatasets.push({
      data: dataset
    })
  }

  getDetailedProject() {

    if (this.basicProjectDTO === undefined) {

    } else {
      this.projectService.setCurrentProjectSubject(this.basicProjectDTO.id);
      this.router.navigate(['todo']).then();
    }

  }


  projectSettings() {
    this.projectService.getSettingsData(this.basicProjectDTO.id).subscribe((res) => {
      const dialogRef = this.dialog.open(ProjectSettingsComponent, {
        data: res,
        width: '600px'
      });

      dialogRef.afterClosed().subscribe(result => {
        console.log(`Dialog result: ${result}`);
      });
    });
  }

}
