import React, { Component } from "react";
import { withRouter } from "react-router";
import Axios from "axios";
import Select from "react-select";

class HistoryPresence extends Component {
  state = {
    presenceDate: "",
    class: "",
    classroom: {},
    presenceListEntries: [],
    classes: [],

    alertType: "",
    alertMessage: ""
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
          <div className="m-2 col-md-3">
            <input
              type="date"
              className="form-control"
              value={this.state.presenceDate}
              onChange={e => {
                this.setState(
                  { presenceDate: e.target.value },
                  this.getPresenceList
                );
              }}
            />
          </div>
        </div>
        <table className="table table-striped table-dark table-hover table-sm">
          <thead className="thead-dark">
            <tr>
              <th scope="col">#</th>
              <th scope="col">Last name</th>
              <th scope="col">First name</th>
              <th scope="col">Status</th>
            </tr>
          </thead>
          <tbody>
            {this.state.presenceListEntries.map(entry => (
              <tr key={"entry_" + entry.id}>
                <td>{this.state.presenceListEntries.indexOf(entry) + 1}</td>
                <td>{entry.child.surname}</td>
                <td>{entry.child.name}</td>
                <td>{entry.presence}</td>
              </tr>
            ))}
          </tbody>
        </table>
        <div className="row">
          <div className={"m-2 alert alert-" + this.state.alertType}>
            {this.state.alertMessage}
          </div>
        </div>
      </div>
    );
  }

  getPresenceList = () => {
    console.log("Getting presence list");

    const requestData = {
      loginData: this.props.session,
      classId: this.props.match.params.classId,
      date: this.state.presenceDate
    };

    console.log(this.props.apiHost + "/presence/list", requestData);

    Axios.post(this.props.apiHost + "/presence/list", requestData)
      .then((res, req) => {
        console.log(res.data);
        this.setState({ presenceListEntries: res.data });
      })
      .catch(err =>
        this.setState({
          alertType: "warning",
          alertMessage: "Problem with contact to the server"
        })
      );
  };

  changePresence = (child, presenceValue) => {
    let obj = {
      presenceDate: this.state.presenceDate,
      presence: presenceValue,
      child: child
    };

    let presenceList = this.state.presenceListEntries.filter(
      preseceElement => preseceElement.child.id !== child.id
    );

    presenceList.push(obj);

    this.setState({ presenceListEntries: presenceList });
  };

  savePresence = () => {
    let requestData = {
      classId: this.props.match.params.classId,
      loginData: this.props.session,
      presenceListEntries: this.state.presenceListEntries,
      presenceListEntry: this.state.presenceListEntries[0],
      date: this.state.presenceDate
    };

    Axios.post(this.props.apiHost + "/presence/add_list", requestData)
      .then((res, req) =>
        this.setState({ alertType: "success", alertMessage: res.data.message })
      )
      .catch(err =>
        this.setState({
          alertType: "success",
          alertMessage:
            "An error ocurred, please try again or contat with admin"
        })
      );
  };

  componentDidMount() {
    let presenceDate = new Date().toISOString().slice(0, 10);
    this.setState({ presenceDate });

    const requestData = {
      loginData: this.props.session,
      classId: this.props.match.params.classId,
      date: presenceDate
    };

    console.log(this.props.apiHost + "/presence/list", requestData);

    Axios.post(this.props.apiHost + "/presence/list", requestData)
      .then((res, req) => {
        console.log(res.data);
        this.setState({ presenceListEntries: res.data });
      })
      .catch(err =>
        this.setState({
          alertType: "warning",
          alertMessage: "Problem with contact to the server"
        })
      );
  }
}

export default withRouter(HistoryPresence);
