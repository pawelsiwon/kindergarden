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
            <th scope="col" />
          </tr>
        </thead>
        <tbody>
          {this.props.children.map(child => (
            <tr key={child.id}>
              <th scope="row" className="align-middle">
                {this.props.children.indexOf(child) + 1}
              </th>
              <td className="align-middle">{child.name}</td>
              <td className="align-middle">{child.surname}</td>
              <td className="align-middle">{child.pesel}</td>
              <td className="align-middle">{child.phonenumber}</td>
              <td className="align-middle">{child.email}</td>
              <td className="float-right align-middle">
                <button
                  className="btn btn-success m-2"
                  onClick={e => {
                    this.props.goTo("/child/details/" + child.id);
                  }}
                >
                  Details
                </button>
                <button
                  className="btn btn-primary m-2"
                  onClick={e =>
                    this.props.history.push("/child/edit/" + child.id)
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
                      personId: child.id
                    };
                    Axios.post(
                      this.props.apiHost + "/child/delete",
                      requestData
                    )
                      .then((res, req) => {
                        this.props.onDelete(child.id);
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
