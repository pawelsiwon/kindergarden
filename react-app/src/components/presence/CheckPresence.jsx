import React, { Component } from "react";
import { withRouter } from "react-router";
import Axios from "axios";
import Select from "react-select";

class CheckPresence extends Component {
  state = {
    todayDate: "",
    classroom: { childList: [] }
  };
  render() {
    return (
      <div className="container">
        <div className="row">
          <button
            className="btn btn-primary m-2"
            onClick={e =>
              this.props.history.push(
                "/classroom/details/" + this.props.match.params.classId
              )
            }
          >
            Return
          </button>
          <button
            className="btn btn-success m-2"
            onClick={e => this.props.history.push("/classroom/show")}
          >
            Save
          </button>
          <div className="m-2 col-md-3">
            <input
              type="date"
              className="form-control"
              value={this.state.todayDate}
              onChange={e => {
                this.setState({ todayDate: e.target.value });
                this.getPresenceList();
              }}
            />
          </div>
        </div>

        <h1>{Date.getDate}</h1>
        <table className="table table-striped table-dark table-hover table-sm">
          <thead className="thead-dark">
            <tr>
              <th scope="col">#</th>
              <th scope="col">Last name</th>
              <th scope="col">First name</th>
              <th scope="col" />
            </tr>
          </thead>
          <tbody>
            {this.state.classroom.childList.map(child => (
              <tr>
                <td>{this.state.classroom.childList.indexOf(child) + 1}</td>
                <td>{child.surname}</td>
                <td>{child.name}</td>
                <Select
                  options={[
                    { value: "ABSENT", label: "absent" },
                    { value: "PRESENT", label: "present" },
                    { value: "LATE", label: "late" }
                  ]}
                />
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }

  getPresenceList = () => {
    console.log("getPresenceList function");
  };

  componentDidMount() {
    let todayDate = new Date().toISOString().slice(0, 10);
    this.setState({ todayDate });

    const requestData = {
      loginData: this.props.session,
      classId: this.props.match.params.classId
    };

    console.log(this.props.apiHost + "/class/profile", requestData);

    Axios.post(this.props.apiHost + "/class/profile", requestData)
      .then((res, req) => {
        this.setState({ classroom: res.data });
        console.log(res);
        console.log(this.state);
      })
      .catch(err => console.log("Błąd: " + err));
  }
}

export default withRouter(CheckPresence);
