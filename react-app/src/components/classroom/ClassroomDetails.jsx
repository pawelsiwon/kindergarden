import React, { Component } from "react";
import { withRouter } from "react-router";
import Axios from "axios";

class ClassroomDetails extends Component {
  state = {
    classroom: { childList: [], classroom: {} }
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
                "/classroom/checkpresence/" + this.props.match.params.classId
              )
            }
          >
            Check presence
          </button>
          <button
            className="btn btn-primary m-2"
            onClick={e =>
              this.props.history.push(
                "/classroom/historypresence/" + this.props.match.params.classId
              )
            }
          >
            History presence
          </button>
        </div>
        <h1>{this.state.classroom.classroom.name + " classroom details"}</h1>
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
              <tr key={child.id}>
                <td className="align-middle">
                  {this.state.classroom.childList.indexOf(child) + 1}
                </td>
                <td className="align-middle">{child.surname}</td>
                <td className="align-middle">{child.name}</td>
              </tr>
            ))}
          </tbody>
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
