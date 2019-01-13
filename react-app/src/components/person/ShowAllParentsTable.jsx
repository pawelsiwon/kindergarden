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
            <th scope="pesel">Pesel</th>
            <th scope="col">Phone number</th>
            <th scope="col">E-mail</th>
            <th scope="col" />
          </tr>
        </thead>
        <tbody>
          {this.props.parents.map(parent => (
            <tr key={parent.id}>
              <th scope="row" className="align-middle">
                {parent.id}
              </th>
              <td className="align-middle">{parent.name}</td>
              <td className="align-middle">{parent.surname}</td>
              <td className="align-middle">{parent.pesel}</td>
              <td className="align-middle">{parent.phonenumber}</td>
              <td className="align-middle">{parent.email}</td>
              <td className="float-right align-middle">
                <button
                  className="btn btn-success m-2"
                  onClick={e => {
                    this.props.goTo("/person/details/" + parent.id);
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
                      loginData: this.props.session,
                      person: null,
                      personId: parent.id
                    };
                    Axios.post(
                      this.props.apiHost + "/person/delete",
                      requestData
                    )
                      .then((res, req) => {
                        this.props.onDelete(parent.id);
                      })
                      .catch(err => console.log(err));
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
