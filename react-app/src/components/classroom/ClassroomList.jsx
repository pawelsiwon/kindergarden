import React, { Component } from "react";
import { withRouter } from "react-router";
import Axios from "axios";

class ClassroomList extends Component {
  state = {
    classrooms: []
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
          <button
            className="btn btn-success m-2"
            onClick={e => this.props.history.push("/classroom/add")}
          >
            Add new
          </button>
        </div>
        <h3>Classroom list:</h3>
        <table className="table table-striped table-dark table-hover table-sm">
          <thead className="thead-dark">
            <tr>
              <th scope="col">#</th>
              <th scope="col">Classroom name</th>
              <th scope="col">Year start</th>
              <th scope="pesel">Year end</th>
              <th scope="col">Teacher</th>
              <th scope="col" />
            </tr>
          </thead>
          <tbody>
            {this.state.classrooms.map(classroom => (
              <tr key={"tr_classroom.id"}>
                <th scope="row" className="align-middle">
                  {this.state.classrooms.indexOf(classroom) + 1}
                </th>
                <td className="align-middle">{classroom.name}</td>
                <td className="align-middle">{classroom.yearStart}</td>
                <td className="align-middle">{classroom.yearEnd}</td>
                <td className="align-middle">
                  {classroom.person.name + " " + classroom.person.surname}
                </td>
                <td>
                  <button
                    className="btn btn-primary"
                    onClick={() =>
                      this.props.history.push(
                        "/classroom/details/" + classroom.id
                      )
                    }
                  >
                    Details
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }

  componentDidMount() {
    console.log(this.props.apiHost + "/class/list", this.props.session);
    Axios.post(this.props.apiHost + "/class/list", this.props.session)
      .then((res, req) => this.setState({ classrooms: res.data }))
      .catch(err => console.log(err));
  }
}

export default withRouter(ClassroomList);
