import React, { Component } from "react";
import { withRouter } from "react-router";
import Axios from "axios";

class ClassroomDetails extends Component {
  state = {
    classroom: { childs: [] }
  };
  render() {
    return (
      <div className="container">
        <div className="row">
          <button
            className="btn btn-primary m-2"
            onClick={e => this.props.history.push("/classroom/show")}
          >
            Return
          </button>
          <button
            className="btn btn-primary m-2"
            onClick={e =>
              this.props.history.push(
                "/classroom/managechildren/" + this.props.match.params.classId
              )
            }
          >
            Manage childrens
          </button>
        </div>
        <h1>{this.state.classroom.name + " classroom details"}</h1>
        <table className="table table-striped table-dark table-hover table-sm">
          <thead className="thead-dark">
            <tr>
              <th scope="col">#</th>
              <th scope="col">First name</th>
              <th scope="col">Last name</th>
              <th scope="pesel">Pesel</th>
              <th scope="col">Phone number</th>
              <th scope="col">E-mail</th>
              <th scope="col" />
            </tr>
          </thead>
          <tbody />
        </table>
      </div>
    );
  }

  componentDidMount() {
    const requestData = {
      loginData: this.props.session,
      classId: this.props.match.params.classId
    };

    console.log(this.props.apiHost + "/class/profile", requestData);

    Axios.post(this.props.apiHost + "/class/profile", requestData)
      .then((res, req) => {
        this.setState({ classroom: res.data });
        console.log(res);
      })
      .catch(err => console.log("Błąd: " + err));
  }
}

export default withRouter(ClassroomDetails);
