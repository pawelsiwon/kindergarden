import React, { Component } from "react";
import { withRouter } from "react-router";
import Axios from "axios";

class ShowAllParentsTable extends Component {
  state = {};
  render() {
    return (
      <table className="table table-striped table-dark table-hover table-sm">
        <thead className="thead-dark">
          <tr>
            <th scope="col">#</th>
            <th scope="col">First name</th>
            <th scope="col">Last name</th>
            <th scope="col" />
          </tr>
        </thead>
        <tbody>
          {this.props.parents.map(parent => (
            <tr key={parent.id}>
              <th scopr="row">{parent.id}</th>
              <td>{parent.name}</td>
              <td>{parent.lastname}</td>
              <td className="float-right">
                <button
                  className="btn btn-success m-2"
                  onClick={e => {
                    this.props.goTo("/person/details/" + parent.id);
                    console.log("/person/show/" + parent.id);
                  }}
                >
                  Details
                </button>
                <button
                  className="btn btn-primary m-2"
                  onClick={e =>
                    this.props.history.push("/person/edit/" + parent.id)
                  }
                >
                  Edit
                </button>
                <button
                  className="btn btn-warning m-2"
                  onClick={e => {
                    const requestData = {
                      sesion: this.props.session,
                      person: null,
                      personId: parent.id
                    };
                    console.log(requestData);
                    console.log(this.props.apiHost + "/person/delete");
                    Axios.post(
                      this.props.apiHost + "/person/delete",
                      requestData
                    );
                  }}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    );
  }
}

export default withRouter(ShowAllParentsTable);
