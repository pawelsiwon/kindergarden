import React, { Component } from "react";
import { withRouter } from "react-router";
import Select from "react-select";
import Axios from "axios";

class ClassroomManageChildren extends Component {
  state = {};
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
        </div>
        <h3>Manage class</h3>
      </div>
    );
  }

  componentDidMount() {
    const requestData = {
      loginData: this.props.session,
      classId: this.props.match.params.classId
    };
    Axios.post(this.props.apiHost + "/class/profile", requestData)
      .then((res, req) => {
        this.setState({ classroom: res.data });
        console.log(res);
      })
      .catch(err => console.log("Błąd: " + err));
  }
}

export default withRouter(ClassroomManageChildren);
