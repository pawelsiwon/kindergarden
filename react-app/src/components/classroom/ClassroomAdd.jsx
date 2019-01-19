import React, { Component } from "react";
import { withRouter } from "react-router";
import Select from "react-select";
import Axios from "axios";

class ClassroomAdd extends Component {
  state = {
    name: "",
    yearStart: "",
    yearEnd: "",
    teacher: {},
    teachers: [],
    alertType: " ",
    alertMessage: ""
  };
  render() {
    return (
      <div className="container">
        <div className="row">
          <button
            className="btn btn-primary m-2"
            onClick={e => this.props.history.push("/app")}
          >
            Return
          </button>
        </div>
        <h4 className="mb-3">Add new classroom</h4>
        <form className="needs-validation">
          <div className="col-md-4 mb-3">
            <label htmlFor="name">Name</label>
            <input
              id="name"
              className="form-control"
              type="text"
              value={this.state.name}
              onChange={e => this.setState({ name: e.target.value })}
            />
          </div>
          <div className="col-md-4 mb-3">
            <label htmlFor="yearStart">Start date</label>
            <input
              id="yearStart"
              className="form-control"
              type="number"
              pattern="(20[0-9][0-9])"
              min="2019"
              value={this.state.yearStart}
              onChange={e => this.setState({ [e.target.id]: e.target.value })}
            />
          </div>

          <div className="col-md-4 mb-3">
            <label htmlFor="yearEnd">End date</label>
            <input
              id="yearEnd"
              className="form-control"
              type="number"
              pattern="(20[0-9][0-9])"
              min={this.state.yearStart}
              value={this.state.yearEnd}
              onChange={e => this.setState({ [e.target.id]: e.target.value })}
            />
          </div>

          <div className="col-md-4 mb-3">
            <label htmlFor="name">Teacher</label>
            <Select
              options={this.state.teachers}
              onChange={e => this.setState({ teacher: e.value })}
            />
          </div>
          <button
            className="btn btn-success"
            onClick={e => this.addClassroom()}
          >
            Add
          </button>
          <div className={"alert alert-" + this.state.alertType}>
            {this.state.alertMessage}
          </div>
        </form>
      </div>
    );
  }

  addClassroom = () => {
    const requestData = {
      loginData: this.props.session,
      transferedClass: this.state,
      classId: null
    };

    console.log(this.props.apiHost + "/class/add", requestData);

    Axios.post(this.props.apiHost + "/class/add", requestData)
      .then((res, req) =>
        this.setState({ alertMessage: "Added!", alertType: "success" })
      )
      .catch(err => console.log(err));
  };

  componentDidMount() {
    console.log(this.props.apiHost + "/list/teachers", this.props.session);
    Axios.post(this.props.apiHost + "/person/list/teachers", this.props.session)
      .then((res, req) => {
        let obj1 = res.data.map(cl => {
          return { value: cl, label: cl.name + " " + cl.surname };
        });

        this.setState({ teachers: obj1 });

        console.log(this.state.teachers);
      })
      .catch(err => console.log(err));

    this.setState({ yearStart: 2019, yearEnd: 2019 });
  }
}

export default withRouter(ClassroomAdd);
